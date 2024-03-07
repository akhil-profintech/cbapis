import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ITradeEntity } from 'app/entities/trade-entity/trade-entity.model';
import { TradeEntityService } from 'app/entities/trade-entity/service/trade-entity.service';
import { VANumberService } from '../service/va-number.service';
import { IVANumber } from '../va-number.model';
import { VANumberFormService } from './va-number-form.service';

import { VANumberUpdateComponent } from './va-number-update.component';

describe('VANumber Management Update Component', () => {
  let comp: VANumberUpdateComponent;
  let fixture: ComponentFixture<VANumberUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let vANumberFormService: VANumberFormService;
  let vANumberService: VANumberService;
  let tradeEntityService: TradeEntityService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), VANumberUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(VANumberUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(VANumberUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    vANumberFormService = TestBed.inject(VANumberFormService);
    vANumberService = TestBed.inject(VANumberService);
    tradeEntityService = TestBed.inject(TradeEntityService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call TradeEntity query and add missing value', () => {
      const vANumber: IVANumber = { id: 456 };
      const tradeEntity: ITradeEntity = { id: 14794 };
      vANumber.tradeEntity = tradeEntity;

      const tradeEntityCollection: ITradeEntity[] = [{ id: 21526 }];
      jest.spyOn(tradeEntityService, 'query').mockReturnValue(of(new HttpResponse({ body: tradeEntityCollection })));
      const additionalTradeEntities = [tradeEntity];
      const expectedCollection: ITradeEntity[] = [...additionalTradeEntities, ...tradeEntityCollection];
      jest.spyOn(tradeEntityService, 'addTradeEntityToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ vANumber });
      comp.ngOnInit();

      expect(tradeEntityService.query).toHaveBeenCalled();
      expect(tradeEntityService.addTradeEntityToCollectionIfMissing).toHaveBeenCalledWith(
        tradeEntityCollection,
        ...additionalTradeEntities.map(expect.objectContaining),
      );
      expect(comp.tradeEntitiesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const vANumber: IVANumber = { id: 456 };
      const tradeEntity: ITradeEntity = { id: 9506 };
      vANumber.tradeEntity = tradeEntity;

      activatedRoute.data = of({ vANumber });
      comp.ngOnInit();

      expect(comp.tradeEntitiesSharedCollection).toContain(tradeEntity);
      expect(comp.vANumber).toEqual(vANumber);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVANumber>>();
      const vANumber = { id: 123 };
      jest.spyOn(vANumberFormService, 'getVANumber').mockReturnValue(vANumber);
      jest.spyOn(vANumberService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ vANumber });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: vANumber }));
      saveSubject.complete();

      // THEN
      expect(vANumberFormService.getVANumber).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(vANumberService.update).toHaveBeenCalledWith(expect.objectContaining(vANumber));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVANumber>>();
      const vANumber = { id: 123 };
      jest.spyOn(vANumberFormService, 'getVANumber').mockReturnValue({ id: null });
      jest.spyOn(vANumberService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ vANumber: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: vANumber }));
      saveSubject.complete();

      // THEN
      expect(vANumberFormService.getVANumber).toHaveBeenCalled();
      expect(vANumberService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVANumber>>();
      const vANumber = { id: 123 };
      jest.spyOn(vANumberService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ vANumber });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(vANumberService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareTradeEntity', () => {
      it('Should forward to tradeEntityService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(tradeEntityService, 'compareTradeEntity');
        comp.compareTradeEntity(entity, entity2);
        expect(tradeEntityService.compareTradeEntity).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
