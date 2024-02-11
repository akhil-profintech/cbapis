import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { TradeChannelService } from '../service/trade-channel.service';
import { ITradeChannel } from '../trade-channel.model';
import { TradeChannelFormService } from './trade-channel-form.service';

import { TradeChannelUpdateComponent } from './trade-channel-update.component';

describe('TradeChannel Management Update Component', () => {
  let comp: TradeChannelUpdateComponent;
  let fixture: ComponentFixture<TradeChannelUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let tradeChannelFormService: TradeChannelFormService;
  let tradeChannelService: TradeChannelService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), TradeChannelUpdateComponent],
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
      .overrideTemplate(TradeChannelUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TradeChannelUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    tradeChannelFormService = TestBed.inject(TradeChannelFormService);
    tradeChannelService = TestBed.inject(TradeChannelService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const tradeChannel: ITradeChannel = { id: 456 };

      activatedRoute.data = of({ tradeChannel });
      comp.ngOnInit();

      expect(comp.tradeChannel).toEqual(tradeChannel);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITradeChannel>>();
      const tradeChannel = { id: 123 };
      jest.spyOn(tradeChannelFormService, 'getTradeChannel').mockReturnValue(tradeChannel);
      jest.spyOn(tradeChannelService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tradeChannel });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: tradeChannel }));
      saveSubject.complete();

      // THEN
      expect(tradeChannelFormService.getTradeChannel).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(tradeChannelService.update).toHaveBeenCalledWith(expect.objectContaining(tradeChannel));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITradeChannel>>();
      const tradeChannel = { id: 123 };
      jest.spyOn(tradeChannelFormService, 'getTradeChannel').mockReturnValue({ id: null });
      jest.spyOn(tradeChannelService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tradeChannel: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: tradeChannel }));
      saveSubject.complete();

      // THEN
      expect(tradeChannelFormService.getTradeChannel).toHaveBeenCalled();
      expect(tradeChannelService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITradeChannel>>();
      const tradeChannel = { id: 123 };
      jest.spyOn(tradeChannelService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tradeChannel });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(tradeChannelService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
