import React from "react"
import { Button } from './styles';

interface Iprops {
  title: String;
  style?: React.CSSProperties;
}

const SubmitButton: React.FC<Iprops> = (props) => {
    return (
        <Button style={props.style} type="submit">{props.title}</Button>
    )
  };

export default SubmitButton;