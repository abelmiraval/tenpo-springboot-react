import logo from '../../../assets/images/logo.png';

const HeaderComponent = () => {
    return (
        <nav className="fixed top-0 left-0 w-full py-3 px-8 bg-white shadow-sm z-10">
            <div className="max-w-6xl mx-auto">
                <div className="flex justify-between items-center w-full">
                    <img src={logo} alt="Tenpo" className="h-10 w-auto" />
                    <button
                        type="button"
                        className="bg-transparent border-0 cursor-pointer p-2 md:hidden flex items-center justify-center"
                        aria-label="Toggle Navigation"
                    >
                        <span className="relative w-6 h-0.5 bg-gray-800 block before:content-[''] before:absolute before:w-6 before:h-0.5 before:bg-gray-800 before:left-0 before:-top-2 before:transition-all before:duration-300 after:content-[''] after:absolute after:w-6 after:h-0.5 after:bg-gray-800 after:left-0 after:top-2 after:transition-all after:duration-300"></span>
                    </button>
                </div>
            </div>
        </nav>
    );
};

export default HeaderComponent;