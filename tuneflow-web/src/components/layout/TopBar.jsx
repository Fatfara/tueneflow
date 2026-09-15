import {
    ChevronLeft,
    ChevronRight,
    Search,
    Bell,
} from "lucide-react";

import { useNavigate } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";

function TopBar() {
    const navigate = useNavigate();
    const { user } = useAuth();

    return (
        <header className="h-16 shrink-0 bg-zinc-950 border-b border-zinc-800 flex items-center justify-between px-4 md:px-6">

            {/* Navigation */}
            <div className="flex items-center gap-2">

                <button
                    onClick={() => navigate(-1)}
                    className="w-9 h-9 rounded-full bg-zinc-800 flex items-center justify-center text-gray-300 hover:text-white hover:bg-zinc-700 transition"
                >
                    <ChevronLeft size={19} />
                </button>

                <button
                    onClick={() => navigate(1)}
                    className="w-9 h-9 rounded-full bg-zinc-800 flex items-center justify-center text-gray-300 hover:text-white hover:bg-zinc-700 transition"
                >
                    <ChevronRight size={19} />
                </button>

            </div>

            {/* Search */}
            <button
                onClick={() => navigate("/search")}
                className="hidden sm:flex items-center gap-3 flex-1 max-w-md mx-4 md:mx-6 bg-zinc-800 hover:bg-zinc-700 rounded-full px-4 py-2.5 text-gray-400 transition"
            >
                <Search size={18} />

                <span className="text-sm">
          What do you want to play?
        </span>
            </button>

            {/* Mobile Search */}
            <button
                onClick={() => navigate("/search")}
                className="sm:hidden w-9 h-9 rounded-full flex items-center justify-center text-gray-400 hover:text-white hover:bg-zinc-800 transition"
            >
                <Search size={19} />
            </button>

            {/* User */}
            <div className="flex items-center gap-2 md:gap-4">

                <button className="hidden sm:flex w-9 h-9 rounded-full items-center justify-center text-gray-400 hover:text-white hover:bg-zinc-800 transition">
                    <Bell size={19} />
                </button>

                <div className="flex items-center gap-2">

                    <div className="w-9 h-9 rounded-full bg-white text-black flex items-center justify-center font-semibold">
                        {user?.name?.charAt(0)?.toUpperCase() || "U"}
                    </div>

                    <span className="hidden lg:block text-sm font-medium max-w-32 truncate">
            {user?.name || "User"}
          </span>

                </div>

            </div>

        </header>
    );
}

export default TopBar;