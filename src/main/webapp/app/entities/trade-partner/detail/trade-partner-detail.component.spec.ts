import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { TradePartnerDetailComponent } from './trade-partner-detail.component';

describe('TradePartner Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TradePartnerDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: TradePartnerDetailComponent,
              resolve: { tradePartner: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(TradePartnerDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load tradePartner on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', TradePartnerDetailComponent);

      // THEN
      expect(instance.tradePartner).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
