import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { TradeEntityService } from '../service/trade-entity.service';
import { ITradeEntity } from '../trade-entity.model';
import { TradeEntityFormService } from './trade-entity-form.service';

import { TradeEntityUpdateComponent } from './trade-entity-update.component';

describe('TradeEntity Management Update Component', () => {
  let comp: TradeEntityUpdateComponent;
  let fixture: ComponentFixture<TradeEntityUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let tradeEntityFormService: TradeEntityFormService;
  let tradeEntityService: TradeEntityService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), TradeEntityUpdateComponent],
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
      .overrideTemplate(TradeEntityUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TradeEntityUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    tradeEntityFormService = TestBed.inject(TradeEntityFormService);
    tradeEntityService = TestBed.inject(TradeEntityService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const tradeEntity: ITradeEntity = { id: 456 };

      activatedRoute.data = of({ tradeEntity });
      comp.ngOnInit();

      expect(comp.tradeEntity).toEqual(tradeEntity);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITradeEntity>>();
      const tradeEntity = { id: 123 };
      jest.spyOn(tradeEntityFormService, 'getTradeEntity').mockReturnValue(tradeEntity);
      jest.spyOn(tradeEntityService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tradeEntity });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: tradeEntity }));
      saveSubject.complete();

      // THEN
      expect(tradeEntityFormService.getTradeEntity).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(tradeEntityService.update).toHaveBeenCalledWith(expect.objectContaining(tradeEntity));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITradeEntity>>();
      const tradeEntity = { id: 123 };
      jest.spyOn(tradeEntityFormService, 'getTradeEntity').mockReturnValue({ id: null });
      jest.spyOn(tradeEntityService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tradeEntity: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: tradeEntity }));
      saveSubject.complete();

      // THEN
      expect(tradeEntityFormService.getTradeEntity).toHaveBeenCalled();
      expect(tradeEntityService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITradeEntity>>();
      const tradeEntity = { id: 123 };
      jest.spyOn(tradeEntityService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tradeEntity });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(tradeEntityService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
