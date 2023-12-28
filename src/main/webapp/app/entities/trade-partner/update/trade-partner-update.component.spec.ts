import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { TradePartnerService } from '../service/trade-partner.service';
import { ITradePartner } from '../trade-partner.model';
import { TradePartnerFormService } from './trade-partner-form.service';

import { TradePartnerUpdateComponent } from './trade-partner-update.component';

describe('TradePartner Management Update Component', () => {
  let comp: TradePartnerUpdateComponent;
  let fixture: ComponentFixture<TradePartnerUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let tradePartnerFormService: TradePartnerFormService;
  let tradePartnerService: TradePartnerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), TradePartnerUpdateComponent],
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
      .overrideTemplate(TradePartnerUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TradePartnerUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    tradePartnerFormService = TestBed.inject(TradePartnerFormService);
    tradePartnerService = TestBed.inject(TradePartnerService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const tradePartner: ITradePartner = { id: 456 };

      activatedRoute.data = of({ tradePartner });
      comp.ngOnInit();

      expect(comp.tradePartner).toEqual(tradePartner);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITradePartner>>();
      const tradePartner = { id: 123 };
      jest.spyOn(tradePartnerFormService, 'getTradePartner').mockReturnValue(tradePartner);
      jest.spyOn(tradePartnerService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tradePartner });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: tradePartner }));
      saveSubject.complete();

      // THEN
      expect(tradePartnerFormService.getTradePartner).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(tradePartnerService.update).toHaveBeenCalledWith(expect.objectContaining(tradePartner));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITradePartner>>();
      const tradePartner = { id: 123 };
      jest.spyOn(tradePartnerFormService, 'getTradePartner').mockReturnValue({ id: null });
      jest.spyOn(tradePartnerService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tradePartner: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: tradePartner }));
      saveSubject.complete();

      // THEN
      expect(tradePartnerFormService.getTradePartner).toHaveBeenCalled();
      expect(tradePartnerService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITradePartner>>();
      const tradePartner = { id: 123 };
      jest.spyOn(tradePartnerService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tradePartner });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(tradePartnerService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
