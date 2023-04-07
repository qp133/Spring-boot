import { createSlice } from '@reduxjs/toolkit';
import { userService } from '../services/userSevice'; 

const initialState = {
    users: []
}

const userSlice = createSlice({
    name: "user",
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder.addMatcher(userService.endpoints.getUsers.matchFulfilled, (state, action) => {
            state.users = action.payload;
        })
        builder.addMatcher(userService.endpoints.createUser.matchFulfilled, (state, action) => {
            state.users.push(action.payload);
        })
        builder.addMatcher(userService.endpoints.updateUser.matchFulfilled, (state, action) => {
            let index = state.users.findIndex(user => user.id === action.payload.id);
            state.users[index] = action.payload
        })
        builder.addMatcher(userService.endpoints.deleteUser.matchFulfilled, (state, action) => {
            let index = state.users.findIndex(user => user.id === action.payload);
            state.users.splice(index, 1);
        })
        builder.addMatcher(userService.endpoints.uploadAvatar.matchFulfilled, (state, action) => {
            let index = state.users.findIndex(user => user.id === +action.payload.id);
            state.users[index].avatar = action.payload.avatar
        })
    }
});

export const { } = userSlice.actions

export default userSlice.reducer