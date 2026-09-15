import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../../services/api";
import { useAuth } from "../../context/AuthContext";

function Login() {

    const navigate = useNavigate();
    const { login } = useAuth();

    const [form, setForm] = useState({
        email: "",
        password: "",
    });

    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const handleChange = (event) => {
        setForm({
            ...form,
            [event.target.name]: event.target.value,
        });
    };

    const handleSubmit = async (event) => {

        event.preventDefault();

        setError("");
        setLoading(true);

        try {

            const response = await api.post("/auth/login", form);

            const data = response.data;

            login(data.accessToken, {
                id: data.userId,
                name: data.name,
                email: data.email,
                role: data.role,
            });

            navigate("/");

        } catch (error) {

            setError(
                error.response?.data?.message ||
                "Invalid email or password"
            );

        } finally {

            setLoading(false);

        }
    };

    return (
        <div className="min-h-screen bg-black text-white flex items-center justify-center px-4">

            <div className="w-full max-w-md">

                <div className="text-center mb-8">

                    <h1 className="text-4xl font-bold">
                        TuneFlow 🎵
                    </h1>

                    <p className="text-gray-400 mt-2">
                        Listen to what moves you
                    </p>

                </div>

                <div className="bg-zinc-900 rounded-2xl p-8">

                    <h2 className="text-2xl font-semibold mb-6">
                        Welcome back
                    </h2>

                    {error && (
                        <div className="bg-red-500/10 border border-red-500/30 text-red-400 rounded-lg p-3 mb-5 text-sm">
                            {error}
                        </div>
                    )}

                    <form
                        onSubmit={handleSubmit}
                        className="space-y-5"
                    >

                        <div>

                            <label className="block text-sm text-gray-300 mb-2">
                                Email
                            </label>

                            <input
                                type="email"
                                name="email"
                                value={form.email}
                                onChange={handleChange}
                                placeholder="you@example.com"
                                required
                                className="w-full bg-zinc-800 border border-zinc-700 rounded-lg px-4 py-3 outline-none focus:border-white"
                            />

                        </div>

                        <div>

                            <label className="block text-sm text-gray-300 mb-2">
                                Password
                            </label>

                            <input
                                type="password"
                                name="password"
                                value={form.password}
                                onChange={handleChange}
                                placeholder="••••••••"
                                required
                                className="w-full bg-zinc-800 border border-zinc-700 rounded-lg px-4 py-3 outline-none focus:border-white"
                            />

                        </div>

                        <button
                            type="submit"
                            disabled={loading}
                            className="w-full bg-white text-black font-semibold rounded-lg py-3 hover:bg-gray-200 disabled:opacity-50"
                        >
                            {loading ? "Signing in..." : "Sign in"}
                        </button>

                    </form>

                    <p className="text-center text-gray-400 text-sm mt-6">

                        Don't have an account?{" "}

                        <Link
                            to="/register"
                            className="text-white hover:underline"
                        >
                            Create one
                        </Link>

                    </p>

                </div>

            </div>

        </div>
    );
}

export default Login;