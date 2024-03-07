import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ITradeEntity } from 'app/entities/trade-entity/trade-entity.model';
import { TradeEntityService } from 'app/entities/trade-entity/service/trade-entity.service';
import { UpdateVAService } from '../service/update-va.service';
import { IUpdateVA } from '../update-va.model';
import { UpdateVAFormService } from './update-va-form.service';

import { UpdateVAUpdateComponent } from './update-va-update.component';

describe('UpdateVA Management Update Component', () => {
  let comp: UpdateVAUpdateComponent;
  let fixture: ComponentFixture<UpdateVAUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let updateVAFormService: UpdateVAFormService;
  let updateVAService: UpdateVAService;
  let tradeEntityService: TradeEntityService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), UpdateVAUpdateComponent],
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
      .overrideTemplate(UpdateVAUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(UpdateVAUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    updateVAFormService = TestBed.inject(UpdateVAFormService);
    updateVAService = TestBed.inject(UpdateVAService);
    tradeEntityService = TestBed.inject(TradeEntityService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call TradeEntity query and add missing value', () => {
      const updateVA: IUpdateVA = { id: 456 };
      const tradeEntity: ITradeEntity = { id: 16423 };
      updateVA.tradeEntity = tradeEntity;

      const tradeEntityCollection: ITradeEntity[] = [{ id: 25674 }];
      jest.spyOn(tradeEntityService, 'query').mockReturnValue(of(new HttpResponse({ body: tradeEntityCollection })));
      const additionalTradeEntities = [tradeEntity];
      const expectedCollection: ITradeEntity[] = [...additionalTradeEntities, ...tradeEntityCollection];
      jest.spyOn(tradeEntityService, 'addTradeEntityToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ updateVA });
      comp.ngOnInit();

      expect(tradeEntityService.query).toHaveBeenCalled();
      expect(tradeEntityService.addTradeEntityToCollectionIfMissing).toHaveBeenCalledWith(
        tradeEntityCollection,
        ...additionalTradeEntities.map(expect.objectContaining),
      );
      expect(comp.tradeEntitiesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const updateVA: IUpdateVA = { id: 456 };
      const tradeEntity: ITradeEntity = { id: 4235 };
      updateVA.tradeEntity = tradeEntity;

      activatedRoute.data = of({ updateVA });
      comp.ngOnInit();

      expect(comp.tradeEntitiesSharedCollection).toContain(tradeEntity);
      expect(comp.updateVA).toEqual(updateVA);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUpdateVA>>();
      const updateVA = { id: 123 };
      jest.spyOn(updateVAFormService, 'getUpdateVA').mockReturnValue(updateVA);
      jest.spyOn(updateVAService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ updateVA });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: updateVA }));
      saveSubject.complete();

      // THEN
      expect(updateVAFormService.getUpdateVA).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(updateVAService.update).toHaveBeenCalledWith(expect.objectContaining(updateVA));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUpdateVA>>();
      const updateVA = { id: 123 };
      jest.spyOn(updateVAFormService, 'getUpdateVA').mockReturnValue({ id: null });
      jest.spyOn(updateVAService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ updateVA: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: updateVA }));
      saveSubject.complete();

      // THEN
      expect(updateVAFormService.getUpdateVA).toHaveBeenCalled();
      expect(updateVAService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUpdateVA>>();
      const updateVA = { id: 123 };
      jest.spyOn(updateVAService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ updateVA });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(updateVAService.update).toHaveBeenCalled();
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
