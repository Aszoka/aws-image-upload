import React, {useState, useEffect, useCallback} from "react";
import './App.css';
import axios from "axios";
import {useDropzone} from 'react-dropzone'

const UserProfiles = () => {
  const [userProfiles, setUserProfiles] = useState([]);
  const fetchUserProfiles = () => {
    axios.get("http://localhost:8080/userprofile/users").then(res =>{
      console.log(res);
      setUserProfiles(res.data);
    });
  };
  useEffect(() =>{
    fetchUserProfiles();

  }, []);
  return userProfiles.map((userProfile, index)=>{
    return (
        <div key={index}>
            {userProfile.profileId ? ( <img src = {`http://localhost:8080/userprofile/${userProfile.profileId}/image/download`}/> ): null}
            <br/>
            <br/>
          <h1>{userProfile.userName}</h1>
          <p>{userProfile.profileId}</p>
            <MyDropzone {...userProfile}/>
            <br/>
        </div>
    );
  });
};

function App() {
  return (
    <div className="App">
      <UserProfiles />
    </div>
  );
};

function MyDropzone({profileId}) {
  const onDrop = useCallback(acceptedFiles => {

    const file = acceptedFiles[0];

    console.log(file);

    const formData = new FormData();
    formData.append("file", file);

    axios.post(`http://localhost:8080/userprofile/${profileId}/image/upload`,
        formData,
        {
          headers:{
            "Content-Type": "multipart/form-data"
          }
        }
        ).then(() => {
          console.log("file uploaded")
    }).catch(err => {
        console.log(err);
    });
  }, [])
  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
      <div {...getRootProps()}>
        <input {...getInputProps()} />
        {
          isDragActive ?
              <p>Drop the files here ...</p> :
              <p>Drag 'n' drop some files here, or click to select files</p>
        }
      </div>
  )
}

export default App;
