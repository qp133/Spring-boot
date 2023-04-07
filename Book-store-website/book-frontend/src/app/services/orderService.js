
import baseApi from './baseService'

export const orderService = baseApi.injectEndpoints({
    endpoints: (builder) => ({
        getOrders: builder.query({
            query: () => `/orders`
        }),
        getOrderById: builder.query({
            query: (id) => ({
                url: `/orders/${id}`,
                method: "GET",
            })
        }),
        confirmOrder: builder.mutation({
            query: (id) => ({
                url: `/orders/${id}`,
                method: "PUT",
            })
        }),
        deleteOrder: builder.mutation({
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

export const { useGetOrdersQuery,useConfirmOrderMutation,useDeleteOrderMutation,useGetOrderByIdQuery} = orderService