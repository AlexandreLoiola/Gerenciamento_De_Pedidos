import React from "react"
import { Button } from './styles';
import { useNavigate } from 'react-router-dom';

interface Iprops {
  title: String;
  redirectTo: string;
}

const BigButton: React.FC<Iprops> = (props) => {
  
  const navigate = useNavigate();

  const handleButtonClick = () => {
    navigate(props.redirectTo);
  }  
  return (
      <Button onClick={handleButtonClick}>
        {props.title}
      </Button>
  )
};

export default BigButton;