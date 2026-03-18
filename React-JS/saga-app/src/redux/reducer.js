import {
    GET_USER,
    GET_USER_SUCCESS,
    GET_USER_FAILURE
} from './actions';

const initialState = {
    users: [],
    loading: false,
    error: null,
}

export const reducer = (state=initialState,action)=>{
    switch(action.type){
        case GET_USER:
            return {
                ...state,
                loading:true,
            };

        case GET_USER_SUCCESS:
            return {
                ...state,
                loading:false,
                users:action.payload,
            }

        case GET_USER_FAILURE:
            return {
                ...state,
                loading:false,
                error:action.payload,
            }

        default:
            return state;
    }
}