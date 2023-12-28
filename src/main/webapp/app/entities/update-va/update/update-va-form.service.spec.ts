import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../update-va.test-samples';

import { UpdateVAFormService } from './update-va-form.service';

describe('UpdateVA Form Service', () => {
  let service: UpdateVAFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UpdateVAFormService);
  });

  describe('Service methods', () => {
    describe('createUpdateVAFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createUpdateVAFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            updateVirAccId: expect.any(Object),
            finReqId: expect.any(Object),
            subGrpId: expect.any(Object),
            msgId: expect.any(Object),
            cnvId: expect.any(Object),
            extRefId: expect.any(Object),
            bizObjId: expect.any(Object),
            appId: expect.any(Object),
            timestamp: expect.any(Object),
            vaNum: expect.any(Object),
            finPar: expect.any(Object),
            clientCode: expect.any(Object),
            requestDateTime: expect.any(Object),
            reslt: expect.any(Object),
            status: expect.any(Object),
            statusDesc: expect.any(Object),
            errorCode: expect.any(Object),
            responseDateTime: expect.any(Object),
            lastupdatedDateTime: expect.any(Object),
            lastUpdatedBy: expect.any(Object),
            tradeEntity: expect.any(Object),
          }),
        );
      });

      it('passing IUpdateVA should create a new form with FormGroup', () => {
        const formGroup = service.createUpdateVAFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            updateVirAccId: expect.any(Object),
            finReqId: expect.any(Object),
            subGrpId: expect.any(Object),
            msgId: expect.any(Object),
            cnvId: expect.any(Object),
            extRefId: expect.any(Object),
            bizObjId: expect.any(Object),
            appId: expect.any(Object),
            timestamp: expect.any(Object),
            vaNum: expect.any(Object),
            finPar: expect.any(Object),
            clientCode: expect.any(Object),
            requestDateTime: expect.any(Object),
            reslt: expect.any(Object),
            status: expect.any(Object),
            statusDesc: expect.any(Object),
            errorCode: expect.any(Object),
            responseDateTime: expect.any(Object),
            lastupdatedDateTime: expect.any(Object),
            lastUpdatedBy: expect.any(Object),
            tradeEntity: expect.any(Object),
          }),
        );
      });
    });

    describe('getUpdateVA', () => {
      it('should return NewUpdateVA for default UpdateVA initial value', () => {
        const formGroup = service.createUpdateVAFormGroup(sampleWithNewData);

        const updateVA = service.getUpdateVA(formGroup) as any;

        expect(updateVA).toMatchObject(sampleWithNewData);
      });

      it('should return NewUpdateVA for empty UpdateVA initial value', () => {
        const formGroup = service.createUpdateVAFormGroup();

        const updateVA = service.getUpdateVA(formGroup) as any;

        expect(updateVA).toMatchObject({});
      });

      it('should return IUpdateVA', () => {
        const formGroup = service.createUpdateVAFormGroup(sampleWithRequiredData);

        const updateVA = service.getUpdateVA(formGroup) as any;

        expect(updateVA).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IUpdateVA should not enable id FormControl', () => {
        const formGroup = service.createUpdateVAFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewUpdateVA should disable id FormControl', () => {
        const formGroup = service.createUpdateVAFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
