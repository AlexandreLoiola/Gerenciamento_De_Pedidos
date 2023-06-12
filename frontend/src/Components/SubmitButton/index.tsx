import React from "react"
import { Button } from './styles';

interface Iprops {
  title: String;
  style?: React.CSSProperties;
  onClick?: () => void;
}

const SubmitButton: React.FC<Iprops> = (props) => {
    return (
        <Button onClick={props.onClick} style={props.style} type="submit">{props.title}</Button>
    )
  };

export default SubmitButton;