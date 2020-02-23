@patient_registry
Feature: Registration of Patients

  This feature verifies whether a patient can be created and
  can receive prescriptions

  Background:
    Given Servers listed in the following table are up and running
      | createpatient | createprescription | listcosts | listpatients |

  Scenario: Create a patient and add prescriptions
    When I create a new patient
    And I add multiple prescriptions
    Then I should see the patients id in a list of all patients
    And I should see the cumulated costs of prescriptions for that patient