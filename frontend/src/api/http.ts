import axios from 'axios'
import router from '@/router'
import { toast } from '@/services/toast'

const http = axios.create({
  baseURL: '/api',
  withCredentials: true,
  headers: {
    'Content-Type': 'application/json',
  },
})

http.interceptors.response.use(
  (response) => response,
  (error) => {
    const status = error.response?.status
    const url: string = error.config?.url ?? ''

    if (status === 401 && !url.startsWith('/auth/')) {
      router.push('/login')
      return Promise.reject(error)
    }

    toast.fromApiError(error)
    return Promise.reject(error)
  },
)

export default http
