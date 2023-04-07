import { createSlice } from '@reduxjs/toolkit';
import { bookService } from '../services/bookService';

const initialState = {
    books: [],
    pageNumber:1
}

const bookSlice = createSlice({
    name: "book",
    initialState,
    reducers: {
        changePageNumber:(state,action)=>{
            state.pageNumber=action.payload
        }
    },
    extraReducers: (builder) => {
        builder.addMatcher(bookService.endpoints.getBooks.matchFulfilled, (state, action) => {
            for(let i=0;i<action.payload.books.length;i++){
                let index = state.books.findIndex(book => book.id === action.payload.books[i].id);
                if(index===-1){
                    state.books.push(action.payload.books[i]);
                }
            }
        })
        builder.addMatcher(bookService.endpoints.createBook.matchFulfilled, (state, action) => {
          
            state.books.splice(0, 0, action.payload);
          
        })
        builder.addMatcher(bookService.endpoints.updateBook.matchFulfilled, (state, action) => {
            let index = state.books.findIndex(book => book.id === action.payload.id);
            state.books[index] = action.payload
        })
        builder.addMatcher(bookService.endpoints.deleteBook.matchFulfilled, (state, action) => {
            let index = state.books.findIndex(book => book.id === action.payload);
            state.books.splice(index, 1);
        })
        builder.addMatcher(bookService.endpoints.uploadThumbnail.matchFulfilled, (state, action) => {
            console.log(action.payload)
            let index = state.books.findIndex(book => book.id === +action.payload.id);
            state.books[index].thumbnail = action.payload.thumbnail
        })
    }
});

export const { changePageNumber} = bookSlice.actions

export default bookSlice.reducer