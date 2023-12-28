import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../client-codes.test-samples';

import { ClientCodesFormService } from './client-codes-form.service';

describe('ClientCodes Form Service', () => {
  let service: ClientCodesFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientCodesFormService);
  });

  describe('Service methods', () => {
    describe('createClientCodesFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createClientCodesFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            clientCode: expect.any(Object),
            clientCharsCode: expect.any(Object),
            clientName: expect.any(Object),
          }),
        );
      });

      it('passing IClientCodes should create a new form with FormGroup', () => {
        const formGroup = service.createClientCodesFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            clientCode: expect.any(Object),
            clientCharsCode: expect.any(Object),
            clientName: expect.any(Object),
          }),
        );
      });
    });

    describe('getClientCodes', () => {
      it('should return NewClientCodes for default ClientCodes initial value', () => {
        const formGroup = service.createClientCodesFormGroup(sampleWithNewData);

        const clientCodes = service.getClientCodes(formGroup) as any;

        expect(clientCodes).toMatchObject(sampleWithNewData);
      });

      it('should return NewClientCodes for empty ClientCodes initial value', () => {
        const formGroup = service.createClientCodesFormGroup();

        const clientCodes = service.getClientCodes(formGroup) as any;

        expect(clientCodes).toMatchObject({});
      });

      it('should return IClientCodes', () => {
        const formGroup = service.createClientCodesFormGroup(sampleWithRequiredData);

        const clientCodes = service.getClientCodes(formGroup) as any;

        expect(clientCodes).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IClientCodes should not enable id FormControl', () => {
        const formGroup = service.createClientCodesFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewClientCodes should disable id FormControl', () => {
        const formGroup = service.createClientCodesFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
