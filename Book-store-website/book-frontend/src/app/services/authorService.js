import baseApi from './baseService'

export const authorService = baseApi.injectEndpoints({
    endpoints: (builder) => ({
        getAuthors: builder.query({
            query: () => "/authors"
        }),
        createAuthor: builder.mutation({
            query: (data) => ({
                url: "/authors",
                method: "POST",
                body: data
            })
        }),
        updateAuthor: builder.mutation({
            query: ({ id, ...data }) => ({
                url: `/authors/${id}`,
                method: "PUT",
                body: data
            })
        }),
        deleteAuthor: builder.mutation({
            query: (id) => ({
                url: `/authors/${id}`,
                method: "DELETE"
            }),
            transformResponse: (response, meta, arg) => {
                return arg
            }
        }),
    })
})

export const { useGetAuthorsQuery, useCreateAuthorMutation ,useUpdateAuthorMutation,useDeleteAuthorMutation} = authorService