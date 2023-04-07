import { createSlice } from '@reduxjs/toolkit';
import { orderService } from '../services/orderService';
import { orderUserService } from '../services/orderUserService';

const initialState = {
    orders: []
}

const orderSlice = createSlice({
    name: "order",
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder.addMatcher(orderService.endpoints.getOrders.matchFulfilled, (state, action) => {
            state.orders = action.payload;
        })
        builder.addMatcher(orderUserService.endpoints.createOrder.matchFulfilled, (state, action) => {
            state.orders.push(action.payload);
        })
        builder.addMatcher(orderService.endpoints.confirmOrder.matchFulfilled, (state, action) => {
            let index = state.orders.findIndex(order => order.id === action.payload.id);
            state.orders[index] = action.payload
        })
        builder.addMatcher(orderService.endpoints.deleteOrder.matchFulfilled, (state, action) => {
            let index = state.orders.findIndex(order => order.id === action.payload);
            state.orders.splice(index, 1);
        })
    }
});

export const { } = orderSlice.actions

export default orderSlice.reducer