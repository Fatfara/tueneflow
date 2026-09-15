import { Outlet } from "react-router-dom";

import Sidebar from "./Sidebar";
import TopBar from "./TopBar";
import MusicPlayer from "../player/MusicPlayer";
import MobileNav from "./MobileNav";

function AppLayout() {
    return (
        <div className="min-h-screen bg-black text-white">

            <div className="flex min-h-screen">

                {/* Desktop Sidebar */}
                <div className="hidden md:block">
                    <Sidebar />
                </div>

                {/* Main Area */}
                <div className="flex-1 min-w-0 flex flex-col">

                    {/* Top Bar */}
                    <TopBar />

                    {/* Page Content */}
                    <main className="flex-1 overflow-y-auto pb-36 md:pb-24 px-4 md:px-6">
                        <Outlet />
                    </main>

                    {/* Music Player */}
                    <MusicPlayer />
                    <MobileNav />
                </div>

            </div>

        </div>
    );
}

export default AppLayout;