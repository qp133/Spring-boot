import { createSlice } from '@reduxjs/toolkit';
import { bookService } from '../services/bookService';

const initialState = {
    newBooks: [],
    pageNumber:1
}

const bookSlice = createSlice({
    name: "newBook",
    initialState,
    reducers: {
        changePageNumber:(state,action)=>{
            state.pageNumber=action.payload
        }
    },
    extraReducers: (builder) => {
        builder.addMatcher(bookService.endpoints.createBook.matchFulfilled, (state, action) => {
          
            state.newBooks.push(action.payload)
            
        })
        builder.addMatcher(bookService.endpoints.updateBook.matchFulfilled, (state, action) => {
            let index = state.newBooks.findIndex(book => book.id === action.payload.id);
            if(index!==-1){
                state.newBooks[index] = action.payload
            }  
        })
        builder.addMatcher(bookService.endpoints.deleteBook.matchFulfilled, (state, action) => {
            let index = state.newBooks.findIndex(book => book.id === action.payload);
            state.newBooks.splice(index, 1);
        })
        builder.addMatcher(bookService.endpoints.uploadThumbnail.matchFulfilled, (state, action) => {
            console.log(action.payload)
            let index = state.newBooks.findIndex(book => book.id === +action.payload.id);
            if(index!==-1){
                state.newBooks[index].thumbnail = action.payload.thumbnail
            }
           
        })
    }
});

export const { changePageNumber} = bookSlice.actions

export default bookSlice.reducer