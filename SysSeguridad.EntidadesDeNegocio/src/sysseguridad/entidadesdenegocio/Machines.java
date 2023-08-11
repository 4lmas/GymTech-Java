package sysseguridad.entidadesdenegocio;
public class Machines {
    
    /**
     * @param protected define que sera un atributo protegido que no cualquier clase podra acceder a el
     * solo las clases hijas o heredadas, si en dado caso no se pueden guardar datos es necesario cambiar los protected a
     * @param private para poder accceder sin menos restricciones
     */
    protected int Id_Machines;
    protected String Machines_Name;
    protected String Brand;
    protected int Serial_Number;
    protected String Acquisition_Date;
    protected String Maintenance_Date;
    protected String Next_Maintenance_Date;
    
    /**
     *@param public define el constructor para que pudea ser utilizado en otras clases xd
     *@param metodos se le pasan para que puedan ser ingresados los datos desde otra clase
     */
    
    public Machines(int Id_Machines, String Machines_Name, String Brand, int Serial_Number, String Acquisition_Date, String Maintenance_Date, String Next_Maintenance_Date){
        this.Id_Machines = Id_Machines;
        this.Machines_Name = Machines_Name;
        this.Brand = Brand;
        this.Serial_Number = Serial_Number;
        this.Acquisition_Date = Acquisition_Date;
        this.Maintenance_Date = Maintenance_Date;
        this.Next_Maintenance_Date = Next_Maintenance_Date;
    }
    
    /**
     * @param void no retorna nada, solo almacena en la variable
     * @param setters almacenas los datos en los atributos
     * @param getters obtienen los datos que se almacenaron anterior mente en los setters
     * esta madre es encapsulamiento
     */
    
    public void setIdMachines(int Id_Machines){
        this.Id_Machines = Id_Machines;
    }
    public int getIdMachines(){
        return Id_Machines;
    }
    
    public void setMachinesName(String Machines_Name){
        this.Machines_Name = Machines_Name;
    }
    public String getMachinesName(){
        return Machines_Name;
    }
    
    public void setBrand(String Brand){
        this.Brand = Brand;
    }
    public String getBrand(){
        return Brand;
    }
    
    public void setSerialNumber(int Serial_Number){
        this.Serial_Number = Serial_Number;
    }
    public int getSerialNumber(){
        return Serial_Number;
    }
    
    public void setAcquisitionDate(String Acquisition_Date){
        this.Acquisition_Date = Acquisition_Date;
    }
    public String getAcquisitionDate(){
        return Acquisition_Date;
    }
    
    public void setMaintenanceDate(String Maintenance_Date){
        this.Maintenance_Date = Maintenance_Date;
    }
    public String getMaintenanceDate(){
        return Maintenance_Date;
    }
    
    public void setNextMaintenanceDate(String Next_Maintenance_Date){
        this.Next_Maintenance_Date = Next_Maintenance_Date;
    }
}
