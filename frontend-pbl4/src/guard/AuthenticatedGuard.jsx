import { Navigate, Outlet } from "react-router-dom";
import { path } from "../constant/path";
import { useAuthenticated } from "../hooks/useAuthenticated";
export default function AuthenticatedGuard({ children }) {
  const authenticated = useAuthenticated();
  if (!authenticated) {
    return <Navigate to={path.login} />;
  }
  return children ? children : <Outlet />;
}
