import baseApi from './baseService'

export const blogService = baseApi.injectEndpoints({
    endpoints: (builder) => ({
        getBlogs: builder.query({
            query: () => "/blogs"
        }),
        deleteBlog: builder.mutation({
            query: (id) => ({
                url: `/blogs/${id}`,
                method: "DELETE"
            }),
            transformResponse: (response, meta, arg) => {
                return arg
            }
        }),
    })
})

export const { useGetBlogsQuery, useDeleteBlogMutation } = blogService