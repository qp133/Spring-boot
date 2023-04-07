import baseApi from './baseService'

export const categoryService = baseApi.injectEndpoints({
    endpoints: (builder) => ({
        getCategories: builder.query({
            query: () => "/categories"
        }),
        createCategory: builder.mutation({
            query: (data) => ({
                url: "/categories",
                method: "POST",
                body: data
            })
        }),
        updateCategory: builder.mutation({
            query: ({ id, ...data }) => ({
                url: `/categories/${id}`,
                method: "PUT",
                body: data
            })
        }),
        deleteCategory: builder.mutation({
            query: (id) => ({
                url: `/categories/${id}`,
                method: "DELETE"
            }),
            transformResponse: (response, meta, arg) => {
                return arg
            }
        }),
    })
})

export const { useGetCategoriesQuery, useCreateCategoryMutation ,useUpdateCategoryMutation,useDeleteCategoryMutation} = categoryService