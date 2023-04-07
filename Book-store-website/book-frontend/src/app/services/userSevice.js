import baseApi from './baseService'

export const userService = baseApi.injectEndpoints({
    endpoints: (builder) => ({
        getUsers: builder.query({
            query: () => "/users"
        }),
        getUserById: builder.query({
            query: (id) => ({
                url: `/users/${id}`,
                method: "GET",
            })
        }),
        createUser: builder.mutation({
            query: (data) => ({
                url: "/users",
                method: "POST",
                body: data
            })
        }),
        uploadAvatar: builder.mutation({
            query: ({ id, file }) => ({
                url:  `/users/${id}/update-avatar`,
                method: "POST",
                body: file
            }),
            transformResponse: (response, meta, arg) => {
                console.log({response, meta, arg})
                return {
                    id : arg.id,
                    avatar : response.avatar
                }
            }

        }),
        updateUser: builder.mutation({
            query: ({ id, ...data }) => ({
                url: `/users/${id}`,
                method: "PUT",
                body: data
            }),
        }),
        
        deleteUser: builder.mutation({
            query: (id) => ({
                url: `/users/${id}`,
                method: "DELETE"
            }),
            transformResponse: (response, meta, arg) => {
                return arg
            }
        }),
      
    })
})

export const { useGetUsersQuery, useCreateUserMutation ,useUpdateUserMutation,useDeleteUserMutation,useGetUserByIdQuery,useUploadAvatarMutation} = userService