 package com.saturn.machines.hr.pl.EmployeeManagementApplication;
import java.net.URI;
import java.util.Map;
import java.util.Set;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.saturn.machines.hr.bl.exceptions.BLException;
import com.saturn.machines.hr.bl.interfaces.managers.DesignationManagerInterface;
import com.saturn.machines.hr.bl.interfaces.managers.EmployeeManagerInterface;
import com.saturn.machines.hr.bl.interfaces.pojo.DesignationInterface;
import com.saturn.machines.hr.bl.interfaces.pojo.EmployeeInterface;
import com.saturn.machines.hr.bl.managers.DesignationManager;
import com.saturn.machines.hr.bl.managers.EmployeeManager;
import com.saturn.machines.hr.bl.pojo.Designation;
import com.saturn.machines.hr.bl.pojo.Employee;



@RestController
@ComponentScan(basePackages = "com.saturn.machines.hr.bl")
public class helloController {
	
	public DesignationManagerInterface designationManager;

	public EmployeeManagerInterface employeeManager;

	public helloController(DesignationManagerInterface designationManager,EmployeeManagerInterface employeeManager ) throws BLException
	{

		this.designationManager = DesignationManager.getDesignationManager();
		this.employeeManager = EmployeeManager.getEmployeeManager();

	}

	@GetMapping("/designations")
	public ResponseEntity<Set<DesignationInterface>> retrieveAllDesignations() {
		try {
			Set<DesignationInterface> designations = designationManager.getDesignations();
			return ResponseEntity.ok(designations);
		} catch (BLException blException) {
			if (blException.hasGenericException()) {
				return ResponseEntity.badRequest().body(Set.of());
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Set.of());
			}
		}
	}

	@GetMapping("/designations/{code}")
	public ResponseEntity<Object> retrieveDesignation(@PathVariable int code) {
		try {
			DesignationInterface designation = designationManager.getDesignationByCode(code);
			return ResponseEntity.ok(designation);
		} catch (BLException blException) {
			if (blException.hasGenericException()) {
				return ResponseEntity.badRequest().body("Generic Exception: " + blException.getGenericException());
			} else if (blException.hasException("code")) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(blException.getException("code"));
			} else {
				return ResponseEntity.internalServerError().build();
			}
		}
	}


	@GetMapping("/designations/title/{title}")
	public ResponseEntity<Object> getDesignationByTitle(@PathVariable String title) {
		try {
			DesignationInterface designation = designationManager.getDesignationByTitle(title);
			return ResponseEntity.ok(designation);
		} catch (BLException blException) {
			if (blException.hasGenericException()) {
				return ResponseEntity.badRequest().body("Generic Exception: " + blException.getGenericException());
			} else if (blException.hasException("title")) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Title Exception: " + blException.getException("title"));
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
	}



	@PostMapping("/designations")
	public ResponseEntity<Object> addDesignations(@RequestBody Designation designation) {
		try {
			DesignationInterface addedDesignation = designationManager.addDesignation(designation);
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{code}")
					.buildAndExpand(addedDesignation.getCode())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (BLException blException) {
			if (blException.hasGenericException()) {
				return ResponseEntity.badRequest().body("Generic Exception: " + blException.getGenericException());
			} else if (blException.hasException("title")) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("title"));
			} else if (blException.hasException("code")) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("code"));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred: " + blException.getMessage());
			}
		}
	}
	

	@PutMapping("/designations")
	public ResponseEntity<Object> updateDesignations(@RequestBody Designation designation) {
		try {
			DesignationInterface updatedDesignation = 
					designationManager.updateDesignation(designation);
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{code}")
					.buildAndExpand(updatedDesignation.getCode())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (BLException blException) {
			if (blException.hasGenericException()) {
				return ResponseEntity.badRequest().body("Generic Exception: " + blException.getGenericException());
			} else if (blException.hasException("title")) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("title"));
			} else if (blException.hasException("code")) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("code"));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred: " + blException.getMessage());
			}
		}
	}


	@DeleteMapping("/designations/{code}")
	public ResponseEntity<Object> deleteDesignation(@PathVariable int code) {
		try {
			designationManager.removeDesignation(code);
			return ResponseEntity.noContent().build();
		} catch (BLException blException) {
			if (blException.hasGenericException()) {
				return ResponseEntity.badRequest().body("Generic Exception: " + blException.getGenericException());
			} else if (blException.hasException("code")) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(blException.getException("code"));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred: " + blException.getMessage());
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/employees")
	public ResponseEntity<Set<EmployeeInterface>> retrieveAllEmployees() throws BLException {
		Set<EmployeeInterface> employees = employeeManager.getEmployees();
		return ResponseEntity.ok(employees);
	}
	
	
	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<Object> getEmployeeByEmployeeId(@PathVariable String employeeId) {
	    try {
	        EmployeeInterface employee = employeeManager.getEmployeeByEmployeeId(employeeId);
	        return ResponseEntity.ok(employee);
	    } catch (BLException blException) {
	        if (blException.hasGenericException()) {
	            return ResponseEntity.badRequest().body("Generic Exception: " + blException.getGenericException());
	        } else if (blException.hasException("employeeId")) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(blException.getException("employeeId"));
	        } else {
	            return ResponseEntity.internalServerError().build();
	        }
	    }
	}
	
	
	@PostMapping("/employees")
	public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) {
	    try {
	        EmployeeInterface addedEmployee = employeeManager.addEmployee(employee);
	        URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(addedEmployee.getEmployeeId()) // Assuming getId() returns the unique identifier of the employee
	                .toUri();
	        return ResponseEntity.created(location).build();
	    } catch (BLException blException) {
	        if (blException.hasGenericException()) {
	            return ResponseEntity.badRequest().body("Generic Exception: " + blException.getGenericException());
	        } else if (blException.hasException("employeeId")) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("employeeId"));
	        } else if (blException.hasException("name")) { // Example for a name conflict, adjust as needed
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("name"));
	        }else if (blException.hasException("designationCode")) { // Example for a name conflict, adjust as needed
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("designationCode")); 
	        }else if (blException.hasException("dateOfBirth")) { // Example for a name conflict, adjust as needed
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("dateOfBirth")); 
	        }else if (blException.hasException("gender")) { // Example for a name conflict, adjust as needed
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("gender"));
	        }else if (blException.hasException("basicSalary")) { // Example for a name conflict, adjust as needed
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("basicSalary"));
	        }else if (blException.hasException("panNumber")) { // Example for a name conflict, adjust as needed
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("panNumber"));
	        }else if (blException.hasException("aadharCardNumber")) { // Example for a name conflict, adjust as needed
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("aadharCardNumber"));
	        }else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred: " + blException.getMessage());
	        }
	    }
	}

	
	@PutMapping("/employees")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee) {
	    try {
	        // Assuming employeeManager.updateEmployee(employee) will update the employee
	        EmployeeInterface updatedEmployee = employeeManager.updateEmployee(employee);
	        return ResponseEntity.ok().body(updatedEmployee);
	    } catch (BLException blException) {
	        if (blException.hasGenericException()) {
	            return ResponseEntity.badRequest().body("Generic Exception: " + blException.getGenericException());
	        } else if (blException.hasException("employeeId")) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("employeeId"));
	        } else if (blException.hasException("name")) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("name"));
	        } else if (blException.hasException("designationCode")) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("designationCode")); 
	        } else if (blException.hasException("dateOfBirth")) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("dateOfBirth")); 
	        } else if (blException.hasException("gender")) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("gender"));
	        } else if (blException.hasException("basicSalary")) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("basicSalary"));
	        } else if (blException.hasException("panNumber")) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("panNumber"));
	        } else if (blException.hasException("aadharCardNumber")) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(blException.getException("aadharCardNumber"));
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred: " + blException.getMessage());
	        }
	    }
	}

	@DeleteMapping("/employees/{employeeId}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable String employeeId) {
	    try {
	        employeeManager.removeEmployee(employeeId);
	        return ResponseEntity.noContent().build();
	    } catch (BLException blException) {
	        if (blException.hasGenericException()) {
	            return ResponseEntity.badRequest().body("Generic Exception: " + blException.getGenericException());
	        } else if (blException.hasException("employeeId")) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(blException.getException("employeeId"));
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred: " + blException.getMessage());
	        }
	    }
	}





	@GetMapping("/employeesss")
	public ResponseEntity<Map<String,EmployeeInterface>> rallEmployees() throws BLException {
		Map<String,EmployeeInterface> employees = employeeManager.myMethod();
		return ResponseEntity.ok(employees);
	}
	

	
}
