import { createSlice } from '@reduxjs/toolkit';
import { bookService } from '../services/bookService';

const initialState = {
    updateBooks: [],
    
}

const bookSlice = createSlice({
    name: "updateBook",
    initialState,
    reducers: {
        changePageNumber:(state,action)=>{
            state.pageNumber=action.payload
        }
    },
    extraReducers: (builder) => {
     
        builder.addMatcher(bookService.endpoints.updateBook.matchFulfilled, (state, action) => {
            let index = state.updateBooks.findIndex(book => book.id === +action.payload.id);
            if(index!==-1){
                state.updateBooks[index]=action.payload
            }else{
                state.updateBooks.push(action.payload)
            }
            
        })
    
        builder.addMatcher(bookService.endpoints.uploadThumbnail.matchFulfilled, (state, action) => {
            console.log(action.payload)
            let index = state.updateBooks.findIndex(book => book.id === +action.payload.id);
            if(index!=-1){
                state.updateBooks[index].thumbnail = action.payload.thumbnail
            }
            else{
                state.updateBooks.push(action.payload.updateBook)
            }
        })
    }
});

export const { changePageNumber} = bookSlice.actions

export default bookSlice.reducer