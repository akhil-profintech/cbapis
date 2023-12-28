import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { RequestOfferDetailComponent } from './request-offer-detail.component';

describe('RequestOffer Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RequestOfferDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: RequestOfferDetailComponent,
              resolve: { requestOffer: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(RequestOfferDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load requestOffer on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', RequestOfferDetailComponent);

      // THEN
      expect(instance.requestOffer).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
