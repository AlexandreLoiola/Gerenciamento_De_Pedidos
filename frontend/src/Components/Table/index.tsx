import React from "react";
import { Table } from "react-bootstrap";
import TableToggle from "./TableToggle";

interface DataType {
  [key: string]: any;
}

interface IProps {
  columnTitles: string[];
  data: DataType[];
  objectKeys: string[];
}

const ManagementTable: React.FC<IProps> = ({columnTitles, data, objectKeys}) => {

  return (
    <Table striped bordered hover>
      <thead>
        <tr>
          {columnTitles.map((column) => (
            <th key={column}>{column}</th>
          ))}
        </tr>
      </thead>
      <tbody>
        {data.map((data, index) => (
          <tr key={index}>
            <td>{index + 1}</td>
            {objectKeys.map((key) => (
              <td key={key}>
                {typeof data[key] === 'boolean' ? (
                  <TableToggle onToggle={() => true} initialValue={data[key]}/>
                ) : (
                  data[key]
                )}
              </td>
            ))}
          </tr>
        ))}
      </tbody>
    </Table>
  );
};

export default ManagementTable;