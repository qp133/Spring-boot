import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

const baseUserApi = createApi({
    reducerPath: 'baseUserApi',
    baseQuery: fetchBaseQuery({
        baseUrl: 'http://localhost:8080/api/user',
        prepareHeaders: (headers, { getState }) => {
            const token = getState().auth.token
            if (token) {
                headers.set('authorization', `Bearer ${token}`)
            }

            return headers
        },
    }),
    
    endpoints: () => ({})
})

export default baseUserApi;