import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ITradeEntity } from 'app/entities/trade-entity/trade-entity.model';
import { TradeEntityService } from 'app/entities/trade-entity/service/trade-entity.service';
import { InstaAlertService } from '../service/insta-alert.service';
import { IInstaAlert } from '../insta-alert.model';
import { InstaAlertFormService } from './insta-alert-form.service';

import { InstaAlertUpdateComponent } from './insta-alert-update.component';

describe('InstaAlert Management Update Component', () => {
  let comp: InstaAlertUpdateComponent;
  let fixture: ComponentFixture<InstaAlertUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let instaAlertFormService: InstaAlertFormService;
  let instaAlertService: InstaAlertService;
  let tradeEntityService: TradeEntityService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), InstaAlertUpdateComponent],
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
      .overrideTemplate(InstaAlertUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(InstaAlertUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    instaAlertFormService = TestBed.inject(InstaAlertFormService);
    instaAlertService = TestBed.inject(InstaAlertService);
    tradeEntityService = TestBed.inject(TradeEntityService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call TradeEntity query and add missing value', () => {
      const instaAlert: IInstaAlert = { id: 456 };
      const tradeEntity: ITradeEntity = { id: 2999 };
      instaAlert.tradeEntity = tradeEntity;

      const tradeEntityCollection: ITradeEntity[] = [{ id: 25813 }];
      jest.spyOn(tradeEntityService, 'query').mockReturnValue(of(new HttpResponse({ body: tradeEntityCollection })));
      const additionalTradeEntities = [tradeEntity];
      const expectedCollection: ITradeEntity[] = [...additionalTradeEntities, ...tradeEntityCollection];
      jest.spyOn(tradeEntityService, 'addTradeEntityToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ instaAlert });
      comp.ngOnInit();

      expect(tradeEntityService.query).toHaveBeenCalled();
      expect(tradeEntityService.addTradeEntityToCollectionIfMissing).toHaveBeenCalledWith(
        tradeEntityCollection,
        ...additionalTradeEntities.map(expect.objectContaining),
      );
      expect(comp.tradeEntitiesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const instaAlert: IInstaAlert = { id: 456 };
      const tradeEntity: ITradeEntity = { id: 11173 };
      instaAlert.tradeEntity = tradeEntity;

      activatedRoute.data = of({ instaAlert });
      comp.ngOnInit();

      expect(comp.tradeEntitiesSharedCollection).toContain(tradeEntity);
      expect(comp.instaAlert).toEqual(instaAlert);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IInstaAlert>>();
      const instaAlert = { id: 123 };
      jest.spyOn(instaAlertFormService, 'getInstaAlert').mockReturnValue(instaAlert);
      jest.spyOn(instaAlertService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ instaAlert });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: instaAlert }));
      saveSubject.complete();

      // THEN
      expect(instaAlertFormService.getInstaAlert).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(instaAlertService.update).toHaveBeenCalledWith(expect.objectContaining(instaAlert));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IInstaAlert>>();
      const instaAlert = { id: 123 };
      jest.spyOn(instaAlertFormService, 'getInstaAlert').mockReturnValue({ id: null });
      jest.spyOn(instaAlertService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ instaAlert: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: instaAlert }));
      saveSubject.complete();

      // THEN
      expect(instaAlertFormService.getInstaAlert).toHaveBeenCalled();
      expect(instaAlertService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IInstaAlert>>();
      const instaAlert = { id: 123 };
      jest.spyOn(instaAlertService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ instaAlert });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(instaAlertService.update).toHaveBeenCalled();
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
