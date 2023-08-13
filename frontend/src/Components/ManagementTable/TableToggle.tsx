import React, { useEffect, useState } from "react";
import { ToggleContainer, ToggleCircleOn, ToggleCircleOff } from "./styles";

interface IProps {
  onToggle: (value: boolean) => void;
  initialValue: boolean;
}

const TableToggle: React.FC<IProps> = ({ onToggle, initialValue }) => {
  const [isOn, setIsOn] = useState(initialValue);
  useEffect(() => {
    setIsOn(initialValue);
  }, [initialValue]);

  const handleToggle = () => {
    const newValue = !isOn;
    setIsOn(newValue);
    onToggle(newValue);
  };

  return (
    <ToggleContainer isOn={isOn} onClick={handleToggle} >
      {isOn ? <ToggleCircleOn /> : <ToggleCircleOff />}
    </ToggleContainer>
  );
};

export default TableToggle;
