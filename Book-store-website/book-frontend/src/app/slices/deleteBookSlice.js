import { createSlice } from '@reduxjs/toolkit';
import { bookService } from '../services/bookService';

const initialState = {
    deleteBooks: [],
    pageNumber:1
}

const bookSlice = createSlice({
    name: "deleteBook",
    initialState,
    reducers: {
        changePageNumber:(state,action)=>{
            state.pageNumber=action.payload
        }
    },
    extraReducers: (builder) => {
       
        builder.addMatcher(bookService.endpoints.deleteBook.matchFulfilled, (state, action) => {
            state.deleteBooks.push(action.payload)
        })
    
    }
});

export const { changePageNumber} = bookSlice.actions

export default bookSlice.reducer