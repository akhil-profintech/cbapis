import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ITradeEntity } from 'app/entities/trade-entity/trade-entity.model';
import { TradeEntityService } from 'app/entities/trade-entity/service/trade-entity.service';
import { BeneValidationService } from '../service/bene-validation.service';
import { IBeneValidation } from '../bene-validation.model';
import { BeneValidationFormService } from './bene-validation-form.service';

import { BeneValidationUpdateComponent } from './bene-validation-update.component';

describe('BeneValidation Management Update Component', () => {
  let comp: BeneValidationUpdateComponent;
  let fixture: ComponentFixture<BeneValidationUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let beneValidationFormService: BeneValidationFormService;
  let beneValidationService: BeneValidationService;
  let tradeEntityService: TradeEntityService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), BeneValidationUpdateComponent],
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
      .overrideTemplate(BeneValidationUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BeneValidationUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    beneValidationFormService = TestBed.inject(BeneValidationFormService);
    beneValidationService = TestBed.inject(BeneValidationService);
    tradeEntityService = TestBed.inject(TradeEntityService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call TradeEntity query and add missing value', () => {
      const beneValidation: IBeneValidation = { id: 456 };
      const tradeEntity: ITradeEntity = { id: 26541 };
      beneValidation.tradeEntity = tradeEntity;

      const tradeEntityCollection: ITradeEntity[] = [{ id: 10595 }];
      jest.spyOn(tradeEntityService, 'query').mockReturnValue(of(new HttpResponse({ body: tradeEntityCollection })));
      const additionalTradeEntities = [tradeEntity];
      const expectedCollection: ITradeEntity[] = [...additionalTradeEntities, ...tradeEntityCollection];
      jest.spyOn(tradeEntityService, 'addTradeEntityToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ beneValidation });
      comp.ngOnInit();

      expect(tradeEntityService.query).toHaveBeenCalled();
      expect(tradeEntityService.addTradeEntityToCollectionIfMissing).toHaveBeenCalledWith(
        tradeEntityCollection,
        ...additionalTradeEntities.map(expect.objectContaining),
      );
      expect(comp.tradeEntitiesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const beneValidation: IBeneValidation = { id: 456 };
      const tradeEntity: ITradeEntity = { id: 10241 };
      beneValidation.tradeEntity = tradeEntity;

      activatedRoute.data = of({ beneValidation });
      comp.ngOnInit();

      expect(comp.tradeEntitiesSharedCollection).toContain(tradeEntity);
      expect(comp.beneValidation).toEqual(beneValidation);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBeneValidation>>();
      const beneValidation = { id: 123 };
      jest.spyOn(beneValidationFormService, 'getBeneValidation').mockReturnValue(beneValidation);
      jest.spyOn(beneValidationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ beneValidation });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: beneValidation }));
      saveSubject.complete();

      // THEN
      expect(beneValidationFormService.getBeneValidation).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(beneValidationService.update).toHaveBeenCalledWith(expect.objectContaining(beneValidation));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBeneValidation>>();
      const beneValidation = { id: 123 };
      jest.spyOn(beneValidationFormService, 'getBeneValidation').mockReturnValue({ id: null });
      jest.spyOn(beneValidationService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ beneValidation: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: beneValidation }));
      saveSubject.complete();

      // THEN
      expect(beneValidationFormService.getBeneValidation).toHaveBeenCalled();
      expect(beneValidationService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBeneValidation>>();
      const beneValidation = { id: 123 };
      jest.spyOn(beneValidationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ beneValidation });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(beneValidationService.update).toHaveBeenCalled();
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
