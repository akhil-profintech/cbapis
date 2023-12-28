import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../trade-entity.test-samples';

import { TradeEntityFormService } from './trade-entity-form.service';

describe('TradeEntity Form Service', () => {
  let service: TradeEntityFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TradeEntityFormService);
  });

  describe('Service methods', () => {
    describe('createTradeEntityFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createTradeEntityFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            entityId: expect.any(Object),
            entityName: expect.any(Object),
            entityDesc: expect.any(Object),
            entityGST: expect.any(Object),
          }),
        );
      });

      it('passing ITradeEntity should create a new form with FormGroup', () => {
        const formGroup = service.createTradeEntityFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            entityId: expect.any(Object),
            entityName: expect.any(Object),
            entityDesc: expect.any(Object),
            entityGST: expect.any(Object),
          }),
        );
      });
    });

    describe('getTradeEntity', () => {
      it('should return NewTradeEntity for default TradeEntity initial value', () => {
        const formGroup = service.createTradeEntityFormGroup(sampleWithNewData);

        const tradeEntity = service.getTradeEntity(formGroup) as any;

        expect(tradeEntity).toMatchObject(sampleWithNewData);
      });

      it('should return NewTradeEntity for empty TradeEntity initial value', () => {
        const formGroup = service.createTradeEntityFormGroup();

        const tradeEntity = service.getTradeEntity(formGroup) as any;

        expect(tradeEntity).toMatchObject({});
      });

      it('should return ITradeEntity', () => {
        const formGroup = service.createTradeEntityFormGroup(sampleWithRequiredData);

        const tradeEntity = service.getTradeEntity(formGroup) as any;

        expect(tradeEntity).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ITradeEntity should not enable id FormControl', () => {
        const formGroup = service.createTradeEntityFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewTradeEntity should disable id FormControl', () => {
        const formGroup = service.createTradeEntityFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
