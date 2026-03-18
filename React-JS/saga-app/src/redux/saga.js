import { put, call, takeLatest } from "redux-saga/effects";
import axios from "axios";
import { getUserSuccess, getUserFailure, GET_USER } from './actions'

// API Function
const fetchUserData = () => {
    return axios.get("https://jsonplaceholder.typicode.com/users");
}

// Worker Function
function* handleGetUsers() {
    try {
        const response = yield call(fetchUserData);
        yield put(getUserSuccess(response.data));
    } catch (error) {
        yield put(getUserFailure(error));
    }
}

// Watcher function
export function* watcherSaga() {
    yield takeLatest(GET_USER, handleGetUsers)
}