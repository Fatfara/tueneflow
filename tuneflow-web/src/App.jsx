import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "./pages/auth/Login";
import ProtectedRoute from "./components/common/ProtectedRoute";
import AppLayout from "./components/layout/AppLayout";

import Home from "./pages/home/Home";
import Search from "./pages/search/Search";
import Library from "./pages/library/Library";
import LikedSongs from "./pages/library/LikedSongs";


function App() {
    return (
        <BrowserRouter>

            <Routes>

                {/* Public Routes */}

                <Route
                    path="/login"
                    element={<Login />}
                />

                {/* Protected Routes */}

                <Route element={<ProtectedRoute />}>

                    <Route element={<AppLayout />}>

                        <Route
                            path="/"
                            element={<Home />}
                        />

                        <Route
                            path="/search"
                            element={<Search />}
                        />

                        <Route
                            path="/library"
                            element={<Library />}
                        />
                        <Route
                            path="/liked"
                            element={<LikedSongs />}
                        />

                    </Route>

                </Route>

            </Routes>

        </BrowserRouter>
    );
}

export default App;