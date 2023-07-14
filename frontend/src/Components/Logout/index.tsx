import { LogoutIcon, Container} from "./styles";
import { useNavigate } from 'react-router-dom';

interface Iprops {
    redirectTo: string;
}

const Logout: React.FC<Iprops> = (props) => {

    const navigate = useNavigate(); 

    const handleIconClick = () => {
        navigate(props.redirectTo)
    }

    return (
        <Container onClick={handleIconClick}>
            <LogoutIcon />
            <p>SAIR</p>
        </Container>
    )
};

export default Logout;