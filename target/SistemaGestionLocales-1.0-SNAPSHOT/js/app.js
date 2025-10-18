// app.js
const API_BASE_URL = "http://localhost:8080/gestion-inventarios/api";

// Función genérica para manejar peticiones HTTP
async function apiRequest(endpoint, method = "GET", data = null) {
    const url = `${API_BASE_URL}${endpoint}`;
    const options = {
        method,
        headers: { "Content-Type": "application/json" }
    };
    if (data) options.body = JSON.stringify(data);

    try {
        const response = await fetch(url, options);
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(`Error ${response.status}: ${errorText}`);
        }
        // Algunos métodos (como DELETE) no devuelven JSON
        return response.status !== 204 ? await response.json() : null;
    } catch (error) {
        console.error(`Error en la API (${method} ${endpoint}):`, error);
        throw error;
    }
}
