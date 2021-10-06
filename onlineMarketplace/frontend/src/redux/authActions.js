import * as actions from './Constants';
import { login, signup } from '../api/apiCalls';
export const logoutSuccess = () =>{
    return{
        type:actions.LOGOUT_SUCCESS
    };
    
}

export const loginSuccess = (authData)=>{
    return {
        type:actions.LOGIN_SUCCESS,
        payLoad: authData
    }
}

export const updateSuccess=({fullName, image})=>{
    return{
        type:actions.UPDATE_SUCCESS,
        payLoad:{
            fullName, image
        }
    }
}

export const loginHandler = (credentials) =>{
    return async function(dispatch){
        const response= await login(credentials);
            
        const authState={
            ...response.data.user,
            password:credentials.password,
            token: response.data.token
        };
        dispatch(loginSuccess(authState));
        return response;
    }
    
}

export const signUpHandler=(user)=>{
    return async function (dispatch){
        const response = await signup(user);
        return response;
    }
}