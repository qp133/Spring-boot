import { createSlice } from '@reduxjs/toolkit';
import { authorService } from '../services/authorSevice';

const initialState = {
    authors: []
}

const authorSlice = createSlice({
    name: "author",
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder.addMatcher(authorService.endpoints.getAuthors.matchFulfilled, (state, action) => {
            state.authors = action.payload;
        })
        builder.addMatcher(authorService.endpoints.createAuthor.matchFulfilled, (state, action) => {
            state.authors.push(action.payload);
        })
        builder.addMatcher(authorService.endpoints.updateAuthor.matchFulfilled, (state, action) => {
            let index = state.authors.findIndex(categoory => categoory.id === action.payload.id);
            state.authors[index] = action.payload
        })
        builder.addMatcher(authorService.endpoints.deleteAuthor.matchFulfilled, (state, action) => {
            let index = state.authors.findIndex(author => author.id === action.payload);
            state.authors.splice(index, 1);
        })
    }
});

export const { } = authorSlice.actions

export default authorSlice.reducer