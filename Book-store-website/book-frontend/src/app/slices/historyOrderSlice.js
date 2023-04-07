import { createSlice } from '@reduxjs/toolkit';
import { orderService } from '../services/orderService';
import { orderUserService } from '../services/orderUserService';

const initialState = {
    historyOrders: []
}
const historyOrderSlice = createSlice({
    name: "historyOrder",
    initialState,
    reducers: {},
    extraReducers: (builder) => {    
        builder.addMatcher(orderUserService.endpoints.getOrderByUserid.matchFulfilled, (state, action) => {
            state.historyOrders = action.payload;
        })
        builder.addMatcher(orderService.endpoints.confirmOrder.matchFulfilled, (state, action) => {
            let index = state.historyOrders.findIndex(historyOrder => historyOrder.id === action.payload.id);
            state.historyOrders[index] = action.payload
        })
        builder.addMatcher(orderUserService.endpoints.createOrder.matchFulfilled, (state, action) => {
            // state.historyOrders.push(action.payload);
            state.historyOrders.splice(0, 0, action.payload);
        })
    }
});

export const { } = historyOrderSlice.actions

export default historyOrderSlice.reducer