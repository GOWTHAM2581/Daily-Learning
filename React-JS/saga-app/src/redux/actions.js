export const GET_USER = "GET_USER";
export const GET_USER_SUCCESS = "GET_USER_SUCCESS";
export const GET_USER_FAILURE = "GET_USER_FAILURE";

export const getUser = () => {
    return {
        type: GET_USER,
    }
}

export const getUserSuccess = (data) => {
    return {
        type:GET_USER_SUCCESS,
        payload:data,
    }
}

export const getUserFailure = (error) => {
    return {
        type:GET_USER_FAILURE,
        payload:error,
    }
}
