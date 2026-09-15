import { createContext, useContext, useEffect, useState } from "react";
import api from "../services/api";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const token = localStorage.getItem("tuneflow_token");

        if (!token) {
            setLoading(false);
            return;
        }

        api.get("/users/me")
            .then((response) => {
                setUser(response.data);
            })
            .catch(() => {
                localStorage.removeItem("tuneflow_token");
                setUser(null);
            })
            .finally(() => {
                setLoading(false);
            });
    }, []);

    const login = (token, userData) => {
        localStorage.setItem("tuneflow_token", token);
        setUser(userData);
    };

    const logout = () => {
        localStorage.removeItem("tuneflow_token");
        setUser(null);
    };

    return (
        <AuthContext.Provider
            value={{
                user,
                loading,
                login,
                logout,
                isAuthenticated: !!user,
            }}
        >
            {children}
        </AuthContext.Provider>
    );
}

export function useAuth() {
    return useContext(AuthContext);
}