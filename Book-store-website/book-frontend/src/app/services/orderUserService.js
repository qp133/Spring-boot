
import baseUserApi from './baseUserService'

export const orderUserService = baseUserApi.injectEndpoints({
    endpoints: (builder) => ({
        getOrderByUserid: builder.query({
            query: (id) => ({
                url: `/orders/${id}`,
                method: "GET",
            })
        }),
        createOrder: builder.mutation({
            query: (data) => ({
                url: "/orders",
                method: "POST",
                body: data
            })
        }),
        postComment: builder.mutation({
            query: ({bookId,userId,content}) => ({
                url: `/comment/book/${bookId}/user/${userId}`,
                method: "POST",
                body: content
            })
        }),
 
    })
})

export const { useCreateOrderMutation ,useGetOrderByUseridQuery,usePostCommentMutation} = orderUserService