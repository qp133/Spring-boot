
import baseUserApi from './baseUserService'

export const orderItemService = baseUserApi.injectEndpoints({
    endpoints: (builder) => ({
        getOrderItems: builder.query({
            query: (id) => `/orderItems/${id}`
        }),
        createOrderItem: builder.mutation({
            query: (data) => ({
                url: "/orderItems",
                method: "POST",
                body: data
            })
        }),
        updateOrderItem: builder.mutation({
            query: ({ id, ...data }) => ({
                url: `/orderItems/${id}`,
                method: "PUT",
                body: data
            })
        }),
        deleteOrderItem: builder.mutation({
            query: (id) => ({
                url: `/orderItems/${id}`,
                method: "DELETE"
            }),
            transformResponse: (response, meta, arg) => {
                return arg
            }
        }),
    })
})

export const { useGetOrderItemsQuery, useCreateOrderItemMutation ,useUpdateOrderItemMutation,useDeleteOrderItemMutation} = orderItemService