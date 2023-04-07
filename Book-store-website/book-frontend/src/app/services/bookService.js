import baseApi from './baseService'

export const bookService = baseApi.injectEndpoints({
    endpoints: (builder) => ({
        getBooks: builder.query({
            query: (query) => ({
                url:query
            }),
            transformResponse: (response, meta, arg) => {
                
                return {
                  books:response.content,
                  totalPages:response.totalPages,
                  pageNumber:response.number
                }
            }
        }),
        createBook: builder.mutation({
            query: (data) => ({
                url: "/books",
                method: "POST",
                body: data
            })
        }),
        
        updateBook: builder.mutation({
            query: ({ id, ...data }) => ({
                url: `/books/${id}`,
                method: "PUT",
                body: data
            })
        }),
        uploadThumbnail: builder.mutation({
            query: ({ id, file }) => ({
                url:  `/books/${id}/update-thumbnail`,
                method: "POST",
                body: file
            }),
            transformResponse: (response, meta, arg) => {
                console.log({response, meta, arg})
                return {
                    id : arg.id,
                    thumbnail : response.thumbnail,
                    updateBook:response
                }
            }

        }),
        deleteBook: builder.mutation({
            query: (id) => ({
                url: `/books/${id}`,
                method: "DELETE"
            }),
            transformResponse: (response, meta, arg) => {
                return arg
            }
        }),
    })
})

export const { useGetBooksQuery, useCreateBookMutation ,useUpdateBookMutation,useDeleteBookMutation,useUploadThumbnailMutation} = bookService