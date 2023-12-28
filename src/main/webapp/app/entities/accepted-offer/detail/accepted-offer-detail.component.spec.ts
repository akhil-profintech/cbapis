import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { AcceptedOfferDetailComponent } from './accepted-offer-detail.component';

describe('AcceptedOffer Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AcceptedOfferDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AcceptedOfferDetailComponent,
              resolve: { acceptedOffer: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AcceptedOfferDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load acceptedOffer on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AcceptedOfferDetailComponent);

      // THEN
      expect(instance.acceptedOffer).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
