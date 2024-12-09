import { apiClient } from './ApiClient'

export const retrieveAllDesignations
    = () => apiClient.get(`/designations`)

export const deleteDesignationApi
    = (code) => apiClient.delete(`/designations/${code}`)



export const retrieveDesignationByCodeApi
    = (code) => apiClient.get(`/designations/${code}`)






export const updateDesignationApi
    = (designation) => apiClient.put('/designations', designation)






export const createDesignationApi
    = (designation) => apiClient.post('/designations', designation)