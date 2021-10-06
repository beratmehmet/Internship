import axios from "axios";
export const signup = (body)=>{
    return axios.post('/api/1.0/users', body);
}

export const changeLanguage = language =>{
    axios.defaults.headers['accept-language']=language;
}

export const login = creds =>{
   return axios.post('/api/1.0/auth', creds);
}

export const getUsers=(page)=>{
    return axios.get(`/api/1.0/users?page=${page}&size=${10}`);
}

export const addFav = (username,id) =>{
    return axios.post(`/api/1.0/users/${username}/favourites/${id}`);
}

export const deleteFav = (username,id) =>{
    return axios.delete(`/api/1.0/users/${username}/delete-favorite/${id}`)
}

export const addSellerToBlackList = (username,id) =>{
    return axios.post(`/api/1.0/users/${username}/to-blacklist/${id}`);
}

export const removeSellerFromBlackList = (username,id) =>{
    return axios.delete(`/api/1.0/users/${username}/delete-from-blacklist/${id}`);
}

export const setAuthorizationHeader=({isLoggedIn, token})=>{
    if(isLoggedIn){
        const authorizationValue= `Bearer ${token}`;
        axios.defaults.headers['Authorization']=authorizationValue;
    }else{
        delete axios.defaults.headers['Authorization'];
    }
}

export const getUser = username =>{
    return axios.get(`/api/1.0/users/${username}`);
}

export const getProduct = id =>{
    return axios.get(`/api/1.0/product/${id}`);
}

export const getSellers=page=>{
    return axios.get(`/api/1.0/sellers?page=${page}&size=${10}`);
}

export const getSellersWOPage=()=>{
    return axios.get(`/api/1.0/sellerswopage`);
}

export const getSeller=id=>{
    return axios.get(`/api/1.0/sellers/${id}`);
}

export const updateUser = (username, body) =>{
    return axios.put(`/api/1.0/users/${username}`,body);
}

export const changeUserPassword = (username, body) =>{
    return axios.put(`/api/1.0/users/${username}/change-password`,body);
}
export const postProduct = product =>{
    return axios.post('/api/1.0/products', product);
}

export const updateProduct =(id, product) =>{
    return axios.put('/api/1.0/products/'+id, product);
}

export const postSeller = seller =>{
    return axios.post('/api/1.0/sellers', seller);
}

export const updateSeller = (id,seller) =>{
    return axios.put(`/api/1.0/sellers/${id}`, seller);
}
export const getProducts = (page=0) =>{
    return axios.get(`/api/1.0/products?page=${page}&size=10`);
}

export const getProductsOfSeller = (id,page=0) =>{
    return axios.get(`/api/1.0/sellers/${id}/products?page=${page}&size=10`);
}

export const getOldProducts = (sellerId,id) =>{
    const path= sellerId ? `/api/1.0/sellers/${sellerId}/products/${id}` : `/api/1.0/products/${id}`;
    return axios.get(path);
}

export const postProductAttachment = attachment => {
    return axios.post('/api/1.0/product-attachments', attachment);
}

export const deleteProduct=id=>{
    return axios.delete(`/api/1.0/products/${id}`);
}

export const deleteUser=username=>{
    return axios.delete(`/api/1.0/users/${username}`);
}

export const deleteSeller=id=>{
    return axios.delete(`/api/1.0/sellers/${id}`);
}

export const searchProducts = (page=0, body) =>{
    return axios.post(`/api/1.0/products/search?page=${page}&size=10`,body);
}

export const searchUsers = (page=0, body) =>{
    return axios.post(`/api/1.0/users/search?page=${page}&size=10`,body);
}

export const searchSellers = (page=0, body) =>{
    return axios.post(`/api/1.0/sellers/search?page=${page}&size=10`,body);
}
