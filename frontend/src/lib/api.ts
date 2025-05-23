import axios from 'axios';

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080/api/v1',
    headers: {
        'Content-Type': 'application/json',
    },
});

axiosInstance.interceptors.response.use(
    (response) => response,
    (error) => {
        const status = error.response?.status;

        if (status === 401) {
            window.location.href = '/login';
        }

        if (status === 403) {
            alert('No tienes permiso para esta acción.');
        }

        if (status === 404) {
            alert('Recurso no encontrado.');
        }

        if (status >= 500) {
            alert('Error del servidor. Inténtalo más tarde.');
        }

        if (status === 429) {
            alert('Muchos intentos, por favor espera un momento.');
        }

        return Promise.reject(error);
    }
);

export default axiosInstance;
