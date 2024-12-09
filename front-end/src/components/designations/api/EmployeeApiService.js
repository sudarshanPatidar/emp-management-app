import { apiClient } from './ApiClient'

export const retrieveAllEmployees
    = () => apiClient.get(`/employees`)

export const deleteEmployeeApi
    = (employeeId) => apiClient.delete(`/employees/${employeeId}`)

export const retrieveEmployeeByEmployeeIdApi
    = (employeeId) => apiClient.get(`/employees/${employeeId}`)



export const updateEmployeeApi
    = (employee) => apiClient.put('/employees', employee)



export const createEmployeeApi
    = (employee) => apiClient.post('/employees', employee)